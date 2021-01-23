package com.VB2020.repository.IO_implementations;


import com.VB2020.model.Entity;
import com.VB2020.model.ForConsole;
import com.VB2020.model.Label;
import com.VB2020.operations.IO_operations;
import com.VB2020.repository.LabelRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IOLabelRepositoryImpl implements LabelRepository {

    private final static String FILE_NAME = "labels.txt";

    public IOLabelRepositoryImpl() {
    }

    @Override
    public Label getById(Long id) throws Exception {

        List<Label> labels = stringToData(IO_operations.read(FILE_NAME));
        Label current = null;
        for (Label c : labels)
        {
            if (c.getId().equals(id))
            {
                current = c;
                break;
            }
        }

        if (current != null)
        {
            return current;
        }
        throw new Exception(ForConsole.NOT_FIND_ID.getMessage() + id);
    }

    @Override
    public void delete(Label item)
    {
        List<Label> labels = stringToData(IO_operations.read(FILE_NAME));
        Label removelabel = null;
        for (Label c : labels)
        {
            if (c.getId().equals(item.getId()))
            {
                removelabel = c;
                break;
            }
        }
        labels.remove(removelabel);
        IO_operations.writeList(FILE_NAME, dataToString(labels));
    }

    @Override
    public void update(Label item) throws Exception {
        delete(getById(item.getId()));
        save(item);
    }

    @Override
    public void save(Label item)
    {
        IO_operations.write(FILE_NAME, dataToString(item));
    }

    @Override
    public List<Label> getAll()
    {
        return stringToData(IO_operations.read(FILE_NAME));
    }

    @Override
    public Long getLastId()
    {
        List<Label> labels = stringToData(IO_operations.read(FILE_NAME));
        labels.sort(Comparator.comparing(Entity::getId));

        if (labels.size() != 0)
        {
            return labels.get(labels.size() - 1).getId();
        }
        return 0L;
    }

    @Override
    public List<Label> stringToData(List<String> data) {

        List<Label> labels = new ArrayList<>();
        for (String str : data)
        {
            String[] parts = str.split(",");
            Label label = new Label();
            label.setId(Long.parseLong(parts[0]));
            label.setName(parts[1]);
            labels.add(label);
        }
        return labels;
    }

    @Override
    public List<String> dataToString(List<Label> labels)
    {
        List<String> data = new ArrayList<>();
        for (Label c : labels)
        {
            data.add(dataToString(c));
        }
        return data;
    }

    @Override
    public String dataToString(Label label)
    {
        return label.getId() + "," + label.getName();
    }
}
