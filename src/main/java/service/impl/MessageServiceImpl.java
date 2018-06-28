package service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.MessageMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import po.Message;
import service.IMessageService;
import tool.MessageSelect;
import tool.ResultMap;

@Service
public class MessageServiceImpl implements IMessageService {

	@Autowired
	private MessageMapper msgMapper;
	
	@Override
	public List<Message> getMessage(MessageSelect m) {
		List<Message> list= msgMapper.select(m);
		Iterator<Message> i=list.iterator();
		while (i.hasNext()){
		    Message message=i.next();
		    if (message.getState()==0)
		        msgMapper.update(message.getId());
        }
	    return list;
	}

	@Override
    @Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
    public int readMessage(String id) {
		return msgMapper.update(id);
	}


	@Override
    @Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
    public ResultMap<String> deletMessage(String excId) {
		int i=msgMapper.delete(excId);
		int result=i>0?ResultMap.SUCCESS : ResultMap.FAILED;
		return new ResultMap<>(result,"");

	}

	@Override
	public int unread(String uid) {
		return msgMapper.unread(uid);
	}

}
