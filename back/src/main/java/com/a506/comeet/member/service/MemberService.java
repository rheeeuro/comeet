package com.a506.comeet.member.service;

import com.a506.comeet.error.errorcode.CommonErrorCode;
import com.a506.comeet.error.exception.RestApiException;
import com.a506.comeet.member.controller.dto.MemberDuplicationRequestDto;
import com.a506.comeet.member.controller.dto.MemberUpdateRequestDto;
import com.a506.comeet.member.controller.dto.MemberSigninRequestDto;
import com.a506.comeet.member.entity.Member;
import com.a506.comeet.member.repository.MemberRepository;
import com.a506.comeet.room.repository.RoomMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final RoomMemberRepository roomMemberRepository;
    @Transactional
    public Member create(MemberSigninRequestDto req) {
        Member member = Member.builder().
                memberId(req.getMemberId()).
                name(req.getName()).
                password(req.getPassword()).
                email(req.getEmail()).
                nickname(req.getNickname()).build();
        return memberRepository.save(member);
    }

    @Transactional
    public boolean update(MemberUpdateRequestDto req, String memberId){
        Member member = memberRepository.findByMemberIdAndIsDeletedFalse(memberId).orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        member.updateMember(req);
        return true;
    }

    public boolean duplicationValid(MemberDuplicationRequestDto req){
        return memberRepository.memberDuplicationCount(req) == 0;
    }

    @Transactional
    public boolean delete(String memberId) {
        Member member = memberRepository.findByMemberIdAndIsDeletedFalse(memberId).orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        member.deleteSoftly();
        roomMemberRepository.deleteAll(member.getRoomMembers());
        return true;
    }
}
