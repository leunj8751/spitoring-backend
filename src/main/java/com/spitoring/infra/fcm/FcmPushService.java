package com.spitoring.infra.fcm;

import com.google.firebase.messaging.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FcmPushService {

    public void sendToToken(String fcmToken, String title, String body) {
        Message message = Message.builder()
            .setToken(fcmToken)
            .setNotification(Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build())
            .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            log.debug("FCM 발송 성공: {}", response);
        } catch (FirebaseMessagingException e) {
            log.error("FCM 발송 실패 - token: {}, error: {}", fcmToken, e.getMessage());
        }
    }

    public BatchResponse sendMulticast(List<String> fcmTokens, String title, String body) {
        MulticastMessage message = MulticastMessage.builder()
            .addAllTokens(fcmTokens)
            .setNotification(Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build())
            .build();

        try {
            BatchResponse response = FirebaseMessaging.getInstance().sendEachForMulticast(message);
            log.info("FCM 멀티캐스트 발송 - 성공: {}/{}", response.getSuccessCount(), fcmTokens.size());
            return response;
        } catch (FirebaseMessagingException e) {
            log.error("FCM 멀티캐스트 발송 실패: {}", e.getMessage());
            throw new RuntimeException("FCM 발송 중 오류가 발생했습니다.", e);
        }
    }
}
