import models.Employee;

public class Runner {
    public static void main(String[] args) {
        Employee employee = new Employee("Jack Jarvis Esq.", 50000, 82);

        try {
            GetAndPost.MyGETRequest();
            GetAndPost.PutRequest(employee);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
