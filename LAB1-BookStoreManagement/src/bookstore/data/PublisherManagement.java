/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.data;

import bookstore.management.BException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Utils;

/**
 *
 * @author leyen
 */
public class PublisherManagement {

    private static PublisherManagement instance = new PublisherManagement();

    public static PublisherManagement getInstance() {
        return instance;
    }

    private List<Publisher> publisherList;

    public List<Publisher> getPublisherList() {
        return publisherList;
    }

    private PublisherManagement() {
        this.publisherList = new ArrayList<>();

    }

    public void addNewPublisher() throws BException, IOException {
        Publisher pub = new Publisher();
        pub.input();
        if (searchPublisherById(pub.getId()) != null) {
            System.out.println("This Publisher Id has already existed!");
        } else {
            publisherList.add(pub);
            System.out.println("Successfully!");
        }
        //saveToFile();

    }

    public void deletePublisher() throws BException, IOException {
        String pId = Utils.inputString("Enter the Publisher Id you want delete");

        if (searchPublisherById(pId) == null) {
            System.out.println("Publisher does not exist");
            return;

        }
        Publisher pub = searchPublisherById(pId);

        publisherList.remove(pub);
        System.out.println("Deleted sucessfully");
        //saveToFile();

    }

    public Publisher searchPublisherById(String pId) {
        for (Publisher publisher : publisherList) {
            if (publisher.getId().equalsIgnoreCase(pId)) {
                return publisher;
            }
        }

        return null;
    }

    public void saveToFile() throws BException, IOException {
        File f = new File("Publisher.dat");
        FileWriter fw = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Publisher publisher : publisherList) {
            bw.write(publisher.toString() + "\n");

        }
        bw.close();
        fw.close();

    }

    public void readFile() throws IOException {
        File f = new File("Publisher.dat");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(PublisherManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            String[] info = line.split("[|]");
            String publisherId = info[1].trim();
            String name = info[2].trim();
            String phone = info[3].trim();
            Publisher pub = new Publisher(publisherId, name, phone);
            if (searchPublisherById(pub.getId()) == null) {
                publisherList.add(pub);
            }

        }
        br.close();
        fr.close();
    }


public void printFromFile() throws BException, IOException {
        //readFile();
        if (publisherList.isEmpty()) {
            System.out.println("The list is empty!");
        } else {
            Collections.sort(publisherList);
            System.out.println("---------------------------------------------------------------");
            System.out.println("| PublisherID |              Name              |     Phone    |");
            System.out.println("|-------------+--------------------------------+--------------|");
            for (Publisher publisher : publisherList) {
                System.out.println(publisher);
                System.out.println("|-------------+--------------------------------+--------------|");
            }

        }

    }

}
