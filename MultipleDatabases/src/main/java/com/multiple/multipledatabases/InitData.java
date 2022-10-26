package com.multiple.multipledatabases;

import com.multiple.multipledatabases.domain.db1.Member;
import com.multiple.multipledatabases.domain.db2.Post;
import com.multiple.multipledatabases.repository.db1.MemberRepository;
import com.multiple.multipledatabases.repository.db2.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * fileName     : InitData
 * author       : jungwoo
 * description  :
 */

@Component
@RequiredArgsConstructor
public class InitData {

  private final MemberRepository memberRepository;

  private final PostRepository postRepository;

  @PostConstruct
  public void init(){

    memberRepository.save(Member.builder().name("jungwoo").age(29).build());

    postRepository.save(Post.builder().title("isTitle").content("asdf").build());

  }
}
