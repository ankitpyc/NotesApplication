package com.NotesApplication.database.dto;

import com.mongodb.BasicDBList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MongoMapping {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Note> queryNotes(String text){
        TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matchingAny(text);
        BasicDBList bsonList = new BasicDBList();
        bsonList.add(textCriteria.getCriteriaObject());
        Query query = new Query();
        query.addCriteria(new Criteria("$or").is(bsonList));
        List<Note> note = mongoTemplate.find(query, Note.class);
        return note;
    }

}
