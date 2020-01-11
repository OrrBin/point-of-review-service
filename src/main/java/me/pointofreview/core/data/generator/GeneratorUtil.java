//package me.pointofreview.core.data.generator;
//
//import jdk.jshell.Snippet;
//import me.pointofreview.core.objects.CodeSnippet;
//import me.pointofreview.core.objects.ReportStatus;
//import me.pointofreview.core.objects.Reputation;
//import me.pointofreview.core.objects.User;
//import me.pointofreview.core.objects.Code;
//import me.pointofreview.persistence.ModelDataStore;
//import me.pointofreview.persistence.UserDataStore;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//public class GeneratorUtil {
//    private final ModelDataStore modelDataStore;
//    private final UserDataStore userDataStore;
//
//    @Autowired
//    public GeneratorUtil(@Qualifier("mongoModelDataStore") ModelDataStore modelDataStore, @Qualifier("mongoUserDataStore") UserDataStore userDataStore) {
//        this.userDataStore = userDataStore;
//        this.modelDataStore = modelDataStore;
//    }
//
//    /**
//     * @return the current timestamp
//     */
//    public static long getTimestamp() {
//        Date date = new Date();
//        return date.getTime();
//    }
//
//    public static List<User> generateUsers() {
//        User[] users = new User[]{
//                new User("Yosi", "123", "u1", new Reputation(), new ReportStatus()),
//                new User("Or", "123", "u2", new Reputation(), new ReportStatus()),
//                new User("king", "123", "u3", new Reputation(), new ReportStatus()),
//                new User("Yahav", "123", "u4", new Reputation(), new ReportStatus()),
//                new User("Oz", "123", "u5", new Reputation(), new ReportStatus()),
//                new User("Allen", "123", "u6", new Reputation(), new ReportStatus()),
//                new User("spammer", "123", "u7", new Reputation(), new ReportStatus()),
//                new User("Unicorn294", "123", "u8", new Reputation(), new ReportStatus()),
//                new User("ScrumMaster", "123", "u9", new Reputation(), new ReportStatus())
//        };
//
//        return Arrays.asList(users);
//    }
//
//    public static void generateSnippets() {
//        List<Code> code = Arrays.asList(CodeGenerator.generateCodes());
//
//        Lsnippets = new CodeSnippet[] {
//            new CodeSnippet("s1", 1577883600*1000, "u1", "Is my code efficient?", "I wrote code as part of my homework.",
//                    )
//
//        }
//    }
//
//    public void createDatabase() {
//        userDataStore.resetDatabase();
//        modelDataStore.resetDatabase();
//
//        List<User> users = generateUsers();
//        for (User user : users)
//            userDataStore.createUser(user);
//
//        List<CodeSnippet> snippets = generateSnippets();
//    }
//}
