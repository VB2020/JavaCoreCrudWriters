package com.VB2020.repository.IO_implementations;


import com.VB2020.model.Entity;
import com.VB2020.model.ForConsole;
import com.VB2020.model.Writer;
import com.VB2020.operations.IO_operations;
import com.VB2020.repository.WriterRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IOWriterRepositoryImpl implements WriterRepository
{
    private final static String FILE_NAME = "writers.txt";
    public IOWriterRepositoryImpl() {}

    @Override
    public Writer getById(Long id) throws Exception
    {
        List<Writer> writers = stringToData(IO_operations.read(FILE_NAME));
        Writer current = null;
        for (Writer c : writers)
        {
            if (c.getId().equals(id))
            {
                current = c;
                break;
            }
        }
        if (current != null) {
            return current;
        }
        throw new Exception(ForConsole.NOT_FIND_ID.getMessage() + id);
    }

    @Override
    public void delete(Writer element)
    {
        List<Writer> writers = stringToData( IO_operations.read(FILE_NAME));
        Writer removeWriter = null;
        for (Writer c: writers)
        {
            if (c.getId().equals(element.getId()))
            {
                removeWriter = c;
                break;
            }
        }
        writers.remove(removeWriter);
        IO_operations.writeList(FILE_NAME, dataToString(writers));
    }

    @Override
    public void update(Writer item) throws Exception
    {
        delete(getById(item.getId()));
        save(item);
    }

    @Override
    public void save(Writer item)
    {
        IO_operations.write(FILE_NAME, dataToString(item));
    }

    @Override
    public List<Writer> getAll()
    {
        return stringToData(IO_operations.read(FILE_NAME));
    }

    @Override
    public Long getLastId()
    {
        List<Writer> writers = stringToData( IO_operations.read(FILE_NAME));
        writers.sort(Comparator.comparing(Entity::getId));

        if (writers.size() != 0)
        {
            return writers.get(writers.size() - 1).getId();
        }
        return 0L; // zero of type long!
    }

    @Override
    public List<Writer> stringToData(List<String> elements)
    {
        List<Writer> writers = new ArrayList<>();

        for (String str : elements)
        {
            String[] parts = str.split(",");
            Writer writer = new Writer();
            writer.setId(Long.parseLong(parts[0]));
            writer.setFirstName(parts[1]);
            writer.setLastName(parts[2]);
            writers.add(writer);
        }
        return writers;
    }

    @Override
    public List<String> dataToString(List<Writer> elements)
    {
        List<String> data = new ArrayList<>();
        for (Writer c : elements) {
            data.add(dataToString(c));
        }
        return data;
    }

    @Override
    public String dataToString(Writer writer)
    {
        return writer.getId() + "," + writer.getFirstName() + "," + writer.getLastName();
    }
}
