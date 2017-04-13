package dal.users;


import demoData.DemoData;
import models.users.User;
import play.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Created by Esteban Luchsinger on 04.04.2017.
 */
public class UsersRepositoryMock implements UsersRepository {
    private final static int INITIAL_USER_CAPACITY = 10;
    private List<User> users = new ArrayList<>(INITIAL_USER_CAPACITY);

    public UsersRepositoryMock() {
        this.users = DemoData.getInstance().getUsers();
    }

    @Override
    public User getUserById(final Integer userId) {
        return users
                .stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst().orElse(null);
    }

    @Override
    public void registerUser(User user) {
        if(user.getId() == null) {
            Optional<User> maxUserId = this.users.stream().max(Comparator.comparingInt(User::getId));

            if(maxUserId.isPresent())
                user.setId(maxUserId.get().getId());
            else
                user.setId(1);
        }
        users.add(user);
    }

    @Override
    public boolean removeUserById(Integer userId) {
        return false;
    }

    /**
     * Counts how many users are registered
     *
     * @return Returns the amount of registered users.
     */
    @Override
    public long countUsers() {
        return this.users.size();
    }

    /**
     * Resets the complete repository.
     * (Deletes all data)
     *
     * @return True, if resetted successfully.
     */
    @Override
    public void resetRepository() {
        Logger.warn("Resetting the Users Repository");
        this.users.clear();
    }
}
