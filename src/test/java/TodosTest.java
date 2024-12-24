import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям вар");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб", "вар"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка вар",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expectedSimpleTask = {simpleTask};
        Task[] actualSimpleTask = todos.search("Позвонить");
        Assertions.assertArrayEquals(expectedSimpleTask, actualSimpleTask);

        Task[] expectedEpic = {epic};
        Task[] actualEpic = todos.search("леб");
        Assertions.assertArrayEquals(expectedEpic, actualEpic);

        Task[] expectedMeeting1 = {meeting};
        Task[] actualMeeting1 = todos.search("версии");
        Assertions.assertArrayEquals(expectedMeeting1, actualMeeting1);

        Task[] expectedMeeting2 = {meeting};
        Task[] actualMeeting2 = todos.search("НетоБанка");
        Assertions.assertArrayEquals(expectedMeeting2, actualMeeting2);

        Task[] expectedAll = {simpleTask, epic, meeting};
        Task[] actualAll = todos.search("вар");
        Assertions.assertArrayEquals(expectedAll, actualAll);
    }

    @Test
    public void shouldNotSearchTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expectedSimpleTask = {};
        Task[] actualSimpleTask = todos.search("два");
        Assertions.assertArrayEquals(expectedSimpleTask, actualSimpleTask);

        Task[] expectedEpic = {};
        Task[] actualEpic = todos.search("два");
        Assertions.assertArrayEquals(expectedEpic, actualEpic);

        Task[] expectedMeeting = {};
        Task[] actualMeeting = todos.search("два");
        Assertions.assertArrayEquals(expectedMeeting, actualMeeting);
    }
}