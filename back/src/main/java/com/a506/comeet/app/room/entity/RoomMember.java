package com.a506.comeet.app.room.entity;

import com.a506.comeet.app.member.entity.Member;
import com.a506.comeet.common.BaseEntityWithSoftDelete;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class RoomMember extends BaseEntityWithSoftDelete {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public RoomMember(Member member, Room room) {
        this.member = member;
        this.room = room;
    }

    public void joinRoom(){
        this.member.addRoomMember(this);
        this.room.addRoomMember(this);
    }

    public void leaveRoom(){
        deleteSoftly();
        this.member.removeRoomMember(this);
        this.room.removeRoomMember(this);
    }

}