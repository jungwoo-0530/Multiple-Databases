package com.multiple.multipledatabases.controller;

import com.multiple.multipledatabases.domain.db1.Member;
import com.multiple.multipledatabases.domain.db2.Post;
import com.multiple.multipledatabases.repository.db1.MemberRepository;
import com.multiple.multipledatabases.repository.db2.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * fileName     : MemberController
 * author       : jungwoo
 * description  :
 */


@RestController
@RequiredArgsConstructor
public class CommonController {

  private final MemberRepository memberRepository;
  private final PostRepository postRepository;

  @GetMapping("/db1/save")
  public String db1Save() {
    memberRepository.save(Member.builder().name("jungwoo").age(29).build());

    return "db1Save";
  }

  @GetMapping("/db1/find")
  public String db1Find() {
    Member member = memberRepository.findByName("jungwoo");
    return member.getName();
  }
  @GetMapping("/db2/save")
  public String db2Save() {

    postRepository.save(Post.builder().title("hi").content("bye").build());

    return "db2Save";
  }
  @GetMapping("/db2/find")
  public String db2Find() {
    Post post = postRepository.findByTitle("hi");

    return post.getContent();
  }

}
