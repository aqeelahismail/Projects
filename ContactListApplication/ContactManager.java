import java.util.*;
import java.util.HashMap;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList.*;

public class ContactManager {
    
    HashMap<String, Contact> contactList;

    public enum extraInformation
    {
        mobileNumber, //0
        homeNumber, //1
        workNumber, //2
        address, //3
        email, //4
        birthday //5
    }

    public ContactManager()
    {
        contactList = new HashMap<String, Contact>();
    }

    public void addContact(Contact contactToAdd)
    {
        contactList.put(contactToAdd.name, contactToAdd);
    }

    public void deleteContact(String name)
    {
        contactList.remove(name);
        System.out.println("Contact deleted.");
    }

    public void updateName(String oldName, String newName)
    {
        Contact toUpdate = contactList.get(oldName);
        toUpdate.updateName(newName);

        contactList.remove(oldName);
        contactList.put(newName, toUpdate);
    }

    public void updateGeneral(Contact toUpdate, extraInformation category, String update)
    {   
        switch (category)
        {
            case mobileNumber:
                toUpdate.mobileNumber = update;
            break;

            case homeNumber:
                toUpdate.homeNumber = update;
            break;

            case workNumber:
                toUpdate.workNumber = update;
            break;

            case address:
                toUpdate.address = update;
            break;

            case email:
                toUpdate.email = update;
            break;

            case birthday:
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate newBirthday = LocalDate.parse(update, dateFormat);
                toUpdate.birthday = newBirthday;
            break;
        }

        contactList.put(toUpdate.name, toUpdate);
    }

    public Contact getContact(String name)
    {
        return contactList.get(name);
    }

    public void viewContacts()
    {

        if (contactList.size() == 0)
        {
            System.out.println("No Contacts Saved.");
        } else {
            System.out.println("Contact list:");
            System.out.println("-------------------------------");

            for (String name : contactList.keySet())
            {
                System.out.println(contactList.get(name).toString());
                System.out.println("-------------------------------");
            }
        }
    }

    public boolean checkDuplicateKey(String name)
    {
        if (contactList.get(name) != null)
        {
            return true; // key already exists
        } else {
            return false; // key does not exist
        }
    }

    public void searchContact(String searchName)
    {
        for (String key : contactList.keySet())
        {
            if (key.toLowerCase().contains(searchName.toLowerCase()))
            {
                System.out.println(contactList.get(key)); 
                System.out.println("-------------------------------");
            }
        }
    }

    public static void main(String[] args)
    {
        ContactManager contactApp = new ContactManager();

        System.out.println("Welcome to the Contact List Application.");
        System.out.println("Please select an option:");
        System.out.println("1. Add a Contact" + "\n2. Delete a Contact" + "\n3. Update Contact Details" + "\n4. Search for a contact" + "\n5. View all Contacts" + "\n6. Exit");
        Scanner keyboard = new Scanner(System.in);

        String answer = keyboard.nextLine();

        while (!(answer.equals("6")))
        {
            switch (answer)
            {
                case "1": // add a contact
                    System.out.println("Enter a Name:");
                    String name = keyboard.nextLine();


                    // check valid name entry
                    while (name.trim().length() == 0)
                    {
                        System.out.println("Invalid name entered. Enter a name: ");
                        name = keyboard.nextLine();
                    }

                    // check if exact name already exists in contact list
                    if (contactApp.checkDuplicateKey(name))
                    {
                        System.out.println("Name already exists in contacts.");
                        break;
                    }

                    System.out.println("Enter a Mobile Number:");
                    String mobileNumber = keyboard.nextLine();

                    Contact contactToAdd = new Contact(name, mobileNumber);

                    // record additional information
                    System.out.println("Would you like to enter additional information?");
                    String yesOrNo = keyboard.nextLine();

                    if (yesOrNo.equals("No") || yesOrNo.equals("N") || yesOrNo.equals("no") || yesOrNo.equals("NO") || yesOrNo.equals("n"))
                    {
                        // do nothing
                    } else {
                        System.out.println("Please select information you would like to add:");
                        System.out.println("1. Home Number" + "\n2. Work Number" + "\n3. Email" + "\n4. Address" + "\n5. Birthday" + "\n6. Done");
                        String extraInfoOption = keyboard.nextLine();

                        while (!(extraInfoOption.equals("6")))
                        {
                            System.out.println("Please enter the relevant information:");

                            switch (extraInfoOption)
                            {
                                case "1":
                                    System.out.println("Home Number:");
                                    contactToAdd.homeNumber = keyboard.nextLine();
                                break;

                                case "2":
                                    System.out.println("Work Number:");
                                    contactToAdd.workNumber = keyboard.nextLine();
                                break;

                                case "3":
                                    System.out.println("Email:");
                                    contactToAdd.email = keyboard.nextLine();
                                break;

                                case "4":
                                    System.out.println("Address:");
                                    contactToAdd.address = keyboard.nextLine();
                                break;

                                case "5":
                                    System.out.println("Birthday:(dd-mm-yyyy)");

                                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                    LocalDate newBirthday;
                                    while (true)
                                    {
                                        try {
                                            newBirthday = LocalDate.parse(keyboard.nextLine(), dateFormat);
                                            break;
                                        } catch (Exception e)
                                        {
                                            System.out.println("Date not entered correctly. Please try again (dd-mm-yyyy):");
                                        }
                                    }

                                    contactToAdd.birthday = newBirthday;
                                break;
                            }
                            System.out.println("Please select information you would like to add:");
                            System.out.println("1. Home Number" + "\n2. Work Number" + "\n3. Email" + "\n4. Address" + "\n5. Birthday" + "\n6. Done");
                            extraInfoOption = keyboard.nextLine();
                        }
                    }
                    contactApp.addContact(contactToAdd);

                    if (contactApp.contactList.get(name)!= null)
                    {
                        System.out.println("Contact Added.\n");
                    } else {
                        System.out.println("Error: Contact unable to be added.");
                    }
                break;

                case "2": // delete a contact
                    System.out.println("Enter contact name to delete:");
                    String toDelete = keyboard.nextLine();
                    System.out.println("Are you sure you want to delete " + toDelete + "'s contact?");
                    String deleteOrNot = keyboard.nextLine();

                    if (deleteOrNot.equals("YES") || deleteOrNot.equals("y") || deleteOrNot.equals("Y") || deleteOrNot.equals("Yes") || deleteOrNot.equals("yes"))
                    {
                        contactApp.deleteContact(toDelete);
                        System.out.println("Contact deleted.");
                    } else {
                        System.out.println("Contact not deleted.");
                    }
                break;

                case "3": // update contact details
                    System.out.println("Enter the name of the contact you would like to edit:");
                    Contact contactToUpdate = contactApp.getContact(keyboard.nextLine());

                    System.out.println("What information would you like to edit?");
                    System.out.println("1. Mobile Number" + "\n2. Home Number" + "\n3. Work Number" + "\n4. Email" + "\n5. Address" + "\n6. Birthday" + "\n7. Done");
                    String detailsToEdit = keyboard.nextLine();

                    while (!(detailsToEdit.equals("7")))
                    {
                        switch (detailsToEdit)
                        {
                            case "1":
                                System.out.println("Mobile Number:");
                                contactApp.updateGeneral(contactToUpdate, extraInformation.mobileNumber, keyboard.nextLine());
                            break;

                            case "2":
                                System.out.println("Home Number:");
                                contactApp.updateGeneral(contactToUpdate, extraInformation.homeNumber, keyboard.nextLine());
                            break;

                            case "3":
                                System.out.println("Work:");
                                contactApp.updateGeneral(contactToUpdate, extraInformation.workNumber, keyboard.nextLine());
                            break;

                            case "4":
                                System.out.println("Email:");
                                contactApp.updateGeneral(contactToUpdate, extraInformation.email, keyboard.nextLine());
                            break;

                            case "5":
                                System.out.println("Address:");
                                contactApp.updateGeneral(contactToUpdate, extraInformation.address, keyboard.nextLine());
                            break;

                            case "6":
                                System.out.println("Birthday:");
                                contactApp.updateGeneral(contactToUpdate, extraInformation.birthday, keyboard.nextLine());
                            break;
                        }
                        
                        System.out.println("What information would you like to edit?");
                        System.out.println("1. Mobile Number" + "\n2. Home Number" + "\n3. Work Number" + "\n4. Email" + "\n5. Address" + "\n6. Birthday" + "\n7. Done");
                        detailsToEdit = keyboard.nextLine();
                    }   
                break;

                case "4": // search

                    if (contactApp.contactList.size() == 0)
                    {
                        System.out.println("Contact List is Empty.");
                    } else {
                        System.out.println("Search for:");
                        String searchName = keyboard.nextLine();

                        System.out.println("Results:");
                        System.out.println("-------------------------------");

                        contactApp.searchContact(searchName);

                        System.out.println("All results shown.");
                    }
                break;

                case "5": // view all contacts
                    contactApp.viewContacts();
                break;
            }
            System.out.println("Please select an option:");
            System.out.println("1. Add a Contact" + "\n2. Delete a Contact" + "\n3. Update Contact Details" + "\n4. Search for a contact" + "\n5. View all Contacts" + "\n6. Exit");
            answer = keyboard.nextLine();
        }

        System.out.println("Contact List Application Closed.");
    }
}
