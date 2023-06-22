package ordermanagement.list;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class ObjectList<E extends IObject> extends ArrayList<E> {

    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public final void setFilePath(String filePath) {
        if (filePath != null && !filePath.isBlank()) {
            this.filePath = filePath;
        }
    }

    public ObjectList() {
    }

    public ObjectList(String filePath) {
        setFilePath(filePath);
    }

    public boolean load() {
        try ( Scanner sc = new Scanner(new File(this.filePath))) {
            E obj;
            String data;
            while (sc.hasNextLine()) {
                data = sc.nextLine();
                if (!data.isBlank()) {
                    obj = parseString(data);
                    if (obj != null) {
                        add(obj);
                    }
                }
            }
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(filePath);//?
        }
        return false;
    }

    public boolean save() {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (E e : this) {
                writer.append(e.toString());
                writer.append("\n");
            }
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ObjectList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public int indexOf(Object o) {
        int idx = -1;
        for (int i = 0; i < size(); i++) {
            if (get(i).getId().equals(((E) o).getId())) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    @Override
    public boolean add(E e) {
        int idx = indexOf(e);
        if (idx >= 0) {
            remove(idx);
        }
        return super.add(e);
    }

    @Override
    public void add(int index, E element) {
        int idx = indexOf(element);
        if (idx >= 0) {
            remove(idx);
        }
        super.add(index, element);
    }

    public void show() {
        for (E e : this) {
            e.output();
        }
    }

    public void showFilter(String id) {
        if(filter(id)!=null){
            filter(id).output(); 
        }else{
            System.out.println("No result!");
        }
               
    }
    
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }
    
//    public void showFilter(Date date) {
//        List<E> filterList = filter(date);
//        for (E e : filterList) {
//            e.output();
//        }
//    }
    
    public abstract E filter(String id);
//    public abstract List<E> filter(Date date);
    protected abstract E parseString(String stringObject);
}
