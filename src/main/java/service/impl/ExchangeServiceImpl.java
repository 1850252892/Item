package service.impl;

import java.util.*;

import mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import model.ExcDate;
import po.*;
import service.IExchangeService;
import tool.CustomFormatter;
import tool.ItemParse;
import tool.ResultMap;
import tool.UUIDUtils;

@Service
public class ExchangeServiceImpl implements IExchangeService {

    @Autowired
    private ExchangeMapper excMapper;
    @Autowired
    private MessageMapper msgMapper;
    @Autowired
    private ItemOtherMapper itemOtherMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ItemMapper itemMapper;

    @Override
    @Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
    public ResultMap<String> addExc(Exchange exc) {
        if (exc.getUid_a().equals(exc.getUid_b())) {
            return new ResultMap<>(ResultMap.FAILED, "不能和自己交换");
        }
        excMapper.addExc(exc);//提交交换信息

        ItemOther itemOther = new ItemOther();
        itemOther.setId(exc.getGid_a());
        itemOther.setStatus("LOCK");
        itemOtherMapper.update(itemOther);//将用户用来交换的商品锁定

        Map<String, String> map = new HashMap<>();
        map.put("username", exc.getUid_a());
        User user = userMapper.selcet(map);

        Message m = new Message(UUIDUtils.getUUID(), exc.getUid_b(),
                CustomFormatter.fomartterDateToString("yyyy-MM-dd HH:mm:ss", new Date()),
                Message.TYPE_SWAP, "用户 <a href='/otherUser?username=" + user.getUsername() + "'>" +
                user.getNickname() + "</a> 向你发起了一条交换请求",
                Message.STATE_UNREAD, "/admin/excData?excid=" + exc.getId());//创建一条通知消息

        int result = msgMapper.insert(m) > 0 ? ResultMap.SUCCESS : ResultMap.FAILED;
        return new ResultMap<>(result, exc.getId());
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
    public ResultMap<String> deleteExc(String id) {
        Exchange exc=excMapper.selectExc(id);
        ItemOther itemOther=new ItemOther();
        itemOther.setId(exc.getGid_a());
        itemOther.setStatus("FREE");
        itemOtherMapper.update(itemOther);

        if (exc.getState()==Exchange.SUCCESS){
            Message message = new Message(UUIDUtils.getUUID(), exc.getUid_a(), CustomFormatter.fomartterDateToString(CustomFormatter.baseFormat, new Date()),
                    Message.TYPE_SWAP, "对方取消了交换，本次交换失败", Message.STATE_UNREAD, "/item/excSuc?excId=" + exc.getId());
            msgMapper.insert(message);
        }
        Map<String,String> m=new HashMap<>();
        m.put("id",id);
        m.put("state",Exchange.CANCEL+"");
        int i = excMapper.changeExc(m) > 0 ? ResultMap.SUCCESS : ResultMap.FAILED;
        return new ResultMap<>(i, "");
    }

    @Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
    @Override
    public ResultMap<String> changeExc(Map<String, String> m) {
        Exchange exchange = excMapper.selectExc(m.get("id"));
        ItemOther itemOther;
        itemOther=itemOtherMapper.select(exchange.getGid_b());
        if (itemOther.getStatus().equals("LOCK")){
            return new ResultMap<>(ResultMap.FAILED,"己方的商品不可用，交换失败");
        }

        itemOther=new ItemOther();
        String messageInfo = "";
        if (m.get("state").equals("1")) {
            if (exchange.getState()!=Exchange.SUBMIT){
                return new ResultMap<>(ResultMap.FAILED,"该交换单状态异常，不能进行操作");
            }

            Item item=itemMapper.selectById(exchange.getGid_a());
            if (item == null){
                m.put("id",exchange.getId());
                m.put("state",Exchange.CANCEL+"");
                excMapper.changeExc(m);
                return new ResultMap<>(ResultMap.FAILED,"对方的商品已删除，交换失败");
            }
            item=itemMapper.selectById(exchange.getGid_b());
            if (item == null){
                m.put("id",exchange.getId());
                m.put("state",Exchange.CANCEL+"");
                excMapper.changeExc(m);
                return new ResultMap<>(ResultMap.FAILED,"己方的商品已删除，交换失败");
            }

            messageInfo = "对方已同意你的交换请求";
            itemOther.setId(exchange.getGid_b());//将被交换的商品锁定
            itemOther.setStatus("LOCK");

        } else{
            messageInfo = "对方已拒绝你的交换请求";
            itemOther.setId(exchange.getGid_a());//将之前锁定的交换商品解锁
            itemOther.setStatus("FREE");
        }
        itemOtherMapper.update(itemOther);

        Message message = new Message(UUIDUtils.getUUID(), exchange.getUid_a(), CustomFormatter.fomartterDateToString(CustomFormatter.baseFormat, new Date()),
                Message.TYPE_SWAP, messageInfo, Message.STATE_UNREAD, "/item/excSuc?excId=" + exchange.getId());
        msgMapper.insert(message);

        int i = excMapper.changeExc(m) > 0 ? ResultMap.SUCCESS : ResultMap.FAILED;
        return new ResultMap<>(i, "");
    }

    @Override
    public ExcDate getExcAllDate(String eid) {
        ExcDate item = excMapper.selectExcAllDate(eid);
        String img_a = item.getImg_a();
        String imgs[] = img_a.split("\\*");
        item.setImg_a(imgs[0]);
        String img_b = item.getImg_b();
        String imgs_[] = img_b.split("\\*");
        item.setImg_b(imgs_[0]);
        return item;

    }

    @Override
    public List<ExcDate> getMyExc(String uid_a, String uid_b) {
        // TODO 自动生成的方法存根
        List<ExcDate> excList = excMapper.selectMyExc(uid_a, uid_b);
        return ItemParse.parseExcImg(excList);
    }

    @Override
    public List<ExcDate> getSuc(String uid) {
        List<ExcDate> excList = excMapper.selectSuc(uid);
        return ItemParse.parseExcImg(excList);
    }


}
