package com.klef.fsad.exam.HibernateSkill;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo 
{
    public static void main(String[] args) 
    {
        ClientDemo cd = new ClientDemo();
        cd.addRecord();
        //cd.displayRecord();
    }

    public void addRecord()
    {
        Configuration cfg = new Configuration();
        cfg.configure();

        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();

        Project p = new Project();

        p.setName("KLEF");
        p.setDescription("Spring Boot Project");
        p.setDate("12-03-2026");
        p.setStatus(true);

        Transaction t = session.beginTransaction();

        session.persist(p);

        t.commit();

        System.out.println("Project Inserted Successfully");

        session.close();
        sf.close();
    }

    public void displayRecord()
    {
        Configuration cfg = new Configuration();
        cfg.configure();

        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Project ID");
        int id = sc.nextInt();

        Project p = session.find(Project.class, id);

        if(p != null)
        {
            System.out.println("ID: "+p.getId());
            System.out.println("Name: "+p.getName());
            System.out.println("Description: "+p.getDescription());
            System.out.println("Date: "+p.getDate());
            System.out.println("Status: "+p.isStatus());
        }
        else
        {
            System.out.println("Project Not Found");
        }

        session.close();
        sf.close();
        sc.close();
    }
}