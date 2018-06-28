package service.impl;

import mapper.*;
import model.ItemDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import po.Item;
import po.ItemOther;
import service.IItemService;
import tool.ItemParse;
import tool.ItemSelect;
import tool.ResultMap;

import java.util.List;

@Service
public class ItemServiceImpl implements IItemService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemClassMapper itemClassMapper;
	@Autowired
	private ItemOtherMapper itemOtherMapper;



	@Override
	@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean upload(Item i) {
		ItemOther itemOther=new ItemOther();
		itemOther.setId(i.getId());
		itemOther.setStatus("FREE");
		itemOther.setBrowser(0);
		itemOther.setCollect(0);
		itemOtherMapper.insert(itemOther);
		if (itemMapper.add(i) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResultMap<String> down(String itemId) {
	    ItemOther itemOther=itemOtherMapper.select(itemId);
	    if (itemOther.getStatus().equals("LOCK")){
	        return new ResultMap<>(ResultMap.FAILED,"商品已锁定，删除失败！！！");
        }
		itemOtherMapper.delete(itemId);
        int i=itemMapper.delete(itemId);
        if (i>0){
			return new ResultMap<>(ResultMap.SUCCESS,"商品删除成功");
        }

		return new ResultMap<>(ResultMap.FAILED,"删除失败！！！");
	}

    @Override
    public Item selectById(String id) {
        return itemMapper.selectById(id);
    }

    @Override
    public ItemDetails selectItemDetails(String id) {
		ItemOther itemOther=itemOtherMapper.select(id);
		itemOther.setBrowser(itemOther.getBrowser()+1);
		itemOtherMapper.update(itemOther);
        return itemMapper.selectDetails(id);
    }

    @Override
	public List<Item> find(ItemSelect is) {
		List<Item> IL = itemMapper.select(is);
		IL= ItemParse.parseItemImg(IL);
		return IL;
	}

    @Override
    public Integer itemCount(ItemSelect is) {
        return itemMapper.selectCount(is);
    }

	@Override
	public List<String> selectClass(ItemSelect is) {
		return itemMapper.selectClass(is);
	}

//	@Override
//	@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
//	public boolean deleteImg(String imgPath,int itemId) {
//		Map<String,String> param = new HashMap<>();
//		ItemSelect is=new ItemSelect();
//		is.setId(itemId+"");
//		Item item = find(is).get(0);
//		List<String> imgs=item.getImgpath();
//		StringBuilder img=new StringBuilder();
//
//		Iterator<String> i=imgs.iterator();
//		while(i.hasNext()){
//			String path=i.next();
//			if(path!=imgPath)
//				img.append(path+"*");
//		}
//		img.deleteCharAt(img.length()-1);
//
//		param.clear();
//		param.put("id", String.valueOf(itemId));
//		param.put("img", img.toString());
//
//        return itemMapper.Update(param) != 0;
//    }

//	@Override
//	public List<Item> selectFreeItem(Map<String, String> m) {
//		List<Item> IL = itemMapper.selectFreeItem(m);
//		IL=ItemParse.parseItemImg(IL);
//		return IL;
//	}

//	@Override
//	public ItemAllData getAllDate(String id) {
//		ItemAllData itemDate=new ItemAllData();
//		Map<String, String> param=new HashMap<>();
//		param.put("id", id);
//		Integer temp=null;
//		ItemSelect is=new ItemSelect();
//		is.setId(id+"");
//
//		itemDate.setItem(itemMapper.select(is).get(0));
//		itemDate.setCollect((temp=colMapper.getCollectedCount(id))==null?0:temp.intValue());
//		itemDate.setRq((temp=eMapper.getRequestedCount(id))==null?0:temp.intValue());
//		itemDate.setComment((temp=comMapper.getCount(id))==null?0:temp.intValue());
//		itemDate.setCommentList(comMapper.find(id));
//
//		return itemDate;
//	}

	@Override
	public List<Item> getPopular() {
		return itemMapper.selectPoplarItems();
	}

	@Override
	public List<String> getClassList() {
		return itemClassMapper.getItemClass();
	}

//    @Override
//	@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
//	public boolean updateItemOther(ItemOther io) {
//       Integer i= itemOtherMapper.update(io);
//
//       if (i>0)
//           return true;
//       else
//           return false;
//	}

//    @Override
//    public ItemOther selectItemOther(String id) {
//        return itemOtherMapper.select(id);
//    }
}
