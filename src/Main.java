import java.util.Scanner;

public class Main {

    static Group rootOrg = null; 
    static Scanner scanner = new Scanner(System.in);

    public static void main (String[] args ) {
        String choice = "";

            while (!choice.equalsIgnoreCase("q")) {
                printMenu();
                choice = scanner.nextLine().trim();

                if (choice.equals("1")) {
                    rootOrg = createOrganization();
                    System.out.println();
                    rootOrg.print(0);
                    System.out.println();
                
                } else if (choice.equals("2")) {
                    if (rootOrg == null) {
                        System.out.println("Create the orginization first(choice 1)");
                        System.out.println();
                        continue;

                    }
                    rootOrg.print(0);
                    System.out.println();

                    System.out.println("Give the group name where a person is added: ");
                    String groupName = scanner.nextLine().trim();

                    Group targetGroup = rootOrg.findGroup(groupName);
                    if (targetGroup == null) {
                        System.out.println("Group not found");
                        System.out.println();
                        continue; 
                    }

                    System.out.print("Give the name of the new person (F-name, L-name)");
                    String personName = scanner.nextLine().trim(); 

                    Person newPerson = new Person(personName, 0);
                    targetGroup.add(newPerson);

                    System.out.println();
                    rootOrg.print(0);
                    System.out.println();

                } else if (choice.equals("3")) {
                    if (rootOrg == null) {
                        System.out.println("Create the organization first (choice 1)");
                        System.out.println();
                        continue;
                    }
                    rootOrg.print(0);
                    System.out.println();

                    System.out.println("Give the name of the person to be removed: ");
                    String removeName = scanner.nextLine().trim();

                    boolean removed = rootOrg.remove(removeName); 
                    if (!removed) {
                        System.out.println("Person not found in orginization");
                        System.out.println();
                        continue;
                    }
                    System.out.println();
                    rootOrg.print(0);
                    System.out.println();

                } else if (!choice.equalsIgnoreCase("q")) {
                    System.out.println("Unknown choice, try again.");
                    System.out.println();
                }
            }
            System.out.println("Goodbye.");
    }

    static void printMenu() {
        System.out.println("Organization management system");
        System.out.println("______________________________________________");
        System.out.println("1. Create and print hard coded organization");
        System.out.println("2. Print organization, add person to it and print it");
        System.out.println("3. Print organization, remove person from it and print it");
        System.out.println("Q. Quit the programme");
        System.out.print("Your choice: ");
    }
        //makes hard coded orginization tree
    static Group createOrginization() {
            //level 0 = root
        Group root = new Group("Hervanta Lager ry", "Leo Latvala", 0);
            //level 1 = groups
        Group sales = new Group("Sales department", "John Doe", 1);
        Group it = new Group("IT department", "Doe John", 1);
        Group hr = new Group("HR department", "William Dafoe");
            //level 2 = people and subgroups under sales
        Person p1 = new Person("Donald Duck", "Sales Rep", 2);
        Person p2 = new Person("Clint Eastwood", "Sales Rep", 2);
        sales.add(p1);
        sales.add(p2);
            //level 2 = subgroup under IT
        Group dev = new Group("Development team", "Mauri Numminen");
        Person p3 = new Person("Carl Mannerheim", "Developer", 3);
        Person p4 = new Person("Seamus Kennedy", "Tester", 3); 
        dev.add(p3);
        dev.add(p4);
        it.add(dev);
            //level 2 = persons under HR
        Person p5 = new Person("Volodomyr Zelensky", 2);
        hr.add(p5);

        root.add(sales);
        root.add(it);
        root.add(hr);

        return root; 

    }
}
