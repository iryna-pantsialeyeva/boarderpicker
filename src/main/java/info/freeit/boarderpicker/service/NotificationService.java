package info.freeit.boarderpicker.service;

import info.freeit.boarderpicker.entity.User;

public interface NotificationService {
    void sendNotification(User user);
}
