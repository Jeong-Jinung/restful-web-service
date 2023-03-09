package com.example.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {

    private static List<Member> members = new ArrayList<>();

    private static int usersCount = 3;

    static {
        members.add(new Member(1, "Kenneth", new Date(), "pass1", "701010-1111111"));
        members.add(new Member(2, "Alice", new Date(), "pass2", "701010-2222222"));
        members.add(new Member(3, "Elena", new Date(), "pass3", "701010-3333333"));
    }

    public List<Member> findAll() {
        return members;
    }

    public Member save(Member member) {
        if (member.getId() == null) {
            member.setId(++usersCount);
        }
        members.add(member);
        return member;
    }

    public Member findOne(Integer id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    public Member deleteById(int id) {
        Iterator<Member> iterator = members.iterator();

        while (iterator.hasNext()) {
            Member member = iterator.next();

            if (member.getId() == id) {
                iterator.remove();
                return member;
            }
        }
        return null;
    }



}
