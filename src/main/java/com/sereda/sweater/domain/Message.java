package com.sereda.sweater.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User author;

  public Message(String text, String tag, User user) {
    this.text = text;
    this.tag = tag;
    this.author = user;
  }

  public String getAuthorName(){
    return author != null ? author.getUsername() : "<none>";
  }
}
