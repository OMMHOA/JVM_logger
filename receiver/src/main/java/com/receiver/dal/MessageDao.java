package com.receiver.dal;

import com.receiver.entity.Message;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface MessageDao extends CrudRepository<Message, Integer> {
}
