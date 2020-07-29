package com.sereda.sweater.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String text;
  private String tag;
  private User author;

  public Message(String text, String tag) {
    this.text = text;
    this.tag = tag;
  }
}
