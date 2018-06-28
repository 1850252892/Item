package service.impl;

import mapper.CollectMapper;
import mapper.ItemOtherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import po.Collect;
import po.Item;
import po.ItemOther;
import service.ICollectService;
import tool.ItemParse;
import tool.ResultMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CollectServiceImpl implements ICollectService {

	@Autowired
	private CollectMapper cm;
	@Autowired
	private ItemOtherMapper itemOtherMapper;
	
	@Override
	@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResultMap<Integer> addCollect(Collect c) {
		ItemOther itemOther=itemOtherMapper.select(c.getG_id());
		itemOther.setCollect(itemOther.getCollect()+1);
		itemOtherMapper.update(itemOther);
		int i= cm.insert(c);
		if (i>0){
			return new ResultMap(ResultMap.SUCCESS,itemOther.getCollect());
		}else {
			return new ResultMap(ResultMap.FAILED,-1);
		}
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResultMap<Integer> deleteCollect(String uid, String gid) {
		ItemOther itemOther=itemOtherMapper.select(gid);
		itemOther.setCollect(itemOther.getCollect()-1);
		itemOtherMapper.update(itemOther);
		int i=cm.delete(uid,gid);
		if (i>0){
			return new ResultMap(ResultMap.SUCCESS,itemOther.getCollect());
		}else {
			return new ResultMap(ResultMap.FAILED,-1);
		}
	}


	@Override
	public Collect select(String uid, String gid) {
		Map<String, String> map=new HashMap<>();
		map.put("uid", uid);
		map.put("gid",gid);
		return cm.select(map);
	}

	@Override
	public List<Item> getMyCollectItem(String uid) {
		return ItemParse.parseItemImg(cm.getMyCollect(uid));
	}
	

}