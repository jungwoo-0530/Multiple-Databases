package com.multiple.multipledatabases.repository.db1;

import com.multiple.multipledatabases.domain.db1.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * fileName     : MemberRepository
 * author       : jungwoo
 * description  :
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
  Member findByName(String name);
}
