package com.gk.secondhand.controller;

import com.gk.secondhand.entity.Chat;
import com.gk.secondhand.entity.Friend;
import com.gk.secondhand.entity.User;
import com.gk.secondhand.service.impl.ChatServiceImpl;
import com.gk.secondhand.service.impl.FriendServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatServiceImpl chatService;
    @Autowired
    private FriendServiceImpl friendService;


    @RequestMapping("/chatHistory")
    @ResponseBody
    public Map<String,Object> chatHistory(HttpServletRequest request, int bid){
        User cur_user = (User) request.getSession().getAttribute("cur_user");
        List<Chat> historyList=chatService.selectHistoryList(cur_user.getId(),bid);
        friendService.readMessage(cur_user.getId(),bid);
        chatService.readHistory(cur_user.getId(),bid);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("historyList",historyList);
        return map;
    }
    @RequestMapping("/sendChat")
    @ResponseBody
    @Transactional
    public Chat sendChat(HttpServletRequest request,int bid,String textContent){
        Date d=new Date();//获取时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//转换格式
        User cur_user = (User) request.getSession().getAttribute("cur_user");
        Chat chat=new Chat();
        chat.setBid(bid);
        chat.setUid(cur_user.getId());
        chat.setContent(textContent);
        chat.setSendData(sdf.format(d));
        chat.setSendUser("right");
        chat.setStatus(1);
        chatService.sendChat(chat);
        System.out.println(chat.getId());
        Chat chat2=chat;
        chat2.setUid(bid);
        chat2.setBid(cur_user.getId());
        chat2.setStatus(0);
        chat2.setSendUser("left");
        chatService.sendChat(chat2);
        //给发送的对象增加未读标记
        Friend friend=friendService.selectFriendList(bid,cur_user.getId());
        if(friend!=null){
            friendService.addNoReadNum(bid,cur_user.getId());
        }else{
            Friend newFriend=new Friend();
            newFriend.setBid(cur_user.getId());
            newFriend.setUid(bid);
            newFriend.setBidName(cur_user.getUsername());
            newFriend.setNoReadNum(1);
            friendService.addFriend(newFriend);
        }
        return chat;


    }
}
