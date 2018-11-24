package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static Statement statement;

    public static void main(String[] args) throws SQLException {
        Connection connection;
        ResultSet resultSet;
        final String SQL_Select_Book_Query = "select * from book";
        final String SQL_Select_Author_Query = "select * from author";
        final String SQL_Select_AB_Query = "select author.name" +
                " from author inner join" +
                " (ab inner join book on ab.book_id = book.id)" +
                " on author.id = ab.author_id" +
                " WHERE book.name = 'Book #1'";

        PGSQLConnect pgsqlConnect = new PGSQLConnect();
        connection = pgsqlConnect.getConnection();
        statement = connection.createStatement();

        generateDBData();

        System.out.println("Table Book contains:");
        resultSet = statement.executeQuery(SQL_Select_Book_Query);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("id") + " - " + resultSet.getString("name"));
        }

        System.out.println("\nTable Authors contains:");
        resultSet = statement.executeQuery(SQL_Select_Author_Query);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("id") + " - " + resultSet.getString("name"));
        }

        System.out.println("\nBook #1 was written by the following authors:");
        resultSet = statement.executeQuery(SQL_Select_AB_Query);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name"));
        }

        statement.close();
        connection.close();

        pgsqlConnect.disconnect();
    }

    private static void generateDBData() throws SQLException {
        final String SQL_Insert_AB_Query1 = "DROP TABLE IF EXISTS ab";
        final String SQL_Insert_AB_Query2 = "DROP SEQUENCE IF EXISTS ab_id_seq";
        final String SQL_Insert_AB_Query3 = "CREATE SEQUENCE public.ab_id_seq\n" +
                "  INCREMENT 1\n" +
                "  MINVALUE 1\n" +
                "  MAXVALUE 100000\n" +
                "  START 1\n" +
                "  CACHE 1;";
        final String SQL_Insert_AB_Query4 = "ALTER TABLE public.ab_id_seq\n" +
                "  OWNER TO postgres;";
        final String SQL_Insert_AB_Query5 = "CREATE TABLE public.ab\n" +
                "(\n" +
                "  id integer NOT NULL DEFAULT nextval('ab_id_seq'::regclass),\n" +
                "  author_id integer,\n" +
                "  book_id integer\n" +
                ")\n" +
                "WITH (\n" +
                "  OIDS=FALSE\n" +
                ");";
        final String SQL_Insert_AB_Query6 = "ALTER TABLE public.ab\n" +
                "  OWNER TO postgres;";
        final String SQL_Insert_AB_Query7 = "INSERT INTO ab (author_id, book_id) VALUES" +
                " (1, 1), (1, 2), (1, 3), (2, 1), (2, 3), (3, 1), (3, 2), (4, 1), (5, 2)";

        final String SQL_Insert_Book_Query1 = "DROP TABLE IF EXISTS book";
        final String SQL_Insert_Book_Query2 = "DROP SEQUENCE IF EXISTS book_id_seq";
        final String SQL_Insert_Book_Query3 = "CREATE SEQUENCE public.book_id_seq\n" +
                "  INCREMENT 1\n" +
                "  MINVALUE 1\n" +
                "  MAXVALUE 100000\n" +
                "  START 1\n" +
                "  CACHE 1;";
        final String SQL_Insert_Book_Query4 = "ALTER TABLE public.book_id_seq\n" +
                "  OWNER TO postgres;";
        final String SQL_Insert_Book_Query5 = "CREATE TABLE public.book\n" +
                "(\n" +
                "  id integer NOT NULL DEFAULT nextval('book_id_seq'::regclass),\n" +
                "  name character varying(255)\n" +
                ")\n" +
                "WITH (\n" +
                "  OIDS=FALSE\n" +
                ");";
        final String SQL_Insert_Book_Query6 = "ALTER TABLE public.book\n" +
                "  OWNER TO postgres;";
        final String SQL_Insert_Book_Query7 = "INSERT INTO book (name) VALUES ('Book #1'), ('Book #2'), ('Book #3')";

        final String SQL_Insert_Author_Query1 = "DROP TABLE IF EXISTS author";
        final String SQL_Insert_Author_Query2 = "DROP SEQUENCE IF EXISTS author_id_seq";
        final String SQL_Insert_Author_Query3 = "CREATE SEQUENCE public.author_id_seq\n" +
                "  INCREMENT 1\n" +
                "  MINVALUE 1\n" +
                "  MAXVALUE 100000\n" +
                "  START 1\n" +
                "  CACHE 1;";
        final String SQL_Insert_Author_Query4 = "ALTER TABLE public.author_id_seq\n" +
                "  OWNER TO postgres;";
        final String SQL_Insert_Author_Query5 = "CREATE TABLE public.author\n" +
                "(\n" +
                "  id integer NOT NULL DEFAULT nextval('author_id_seq'::regclass),\n" +
                "  name character varying(255)\n" +
                ")\n" +
                "WITH (\n" +
                "  OIDS=FALSE\n" +
                ");\n";
        final String SQL_Insert_Author_Query6 = "ALTER TABLE public.author\n" +
                "  OWNER TO postgres;";
        final String SQL_Insert_Author_Query7 = "INSERT INTO author (name) VALUES ('Author #1'), ('Author #2'), ('Author #3'), ('Author #4'), ('Author #5')";

        statement.execute(SQL_Insert_Book_Query1);
        statement.execute(SQL_Insert_Book_Query2);
        statement.execute(SQL_Insert_Book_Query3);
        statement.execute(SQL_Insert_Book_Query4);
        statement.execute(SQL_Insert_Book_Query5);
        statement.execute(SQL_Insert_Book_Query6);
        statement.execute(SQL_Insert_Book_Query7);

        statement.execute(SQL_Insert_Author_Query1);
        statement.execute(SQL_Insert_Author_Query2);
        statement.execute(SQL_Insert_Author_Query3);
        statement.execute(SQL_Insert_Author_Query4);
        statement.execute(SQL_Insert_Author_Query5);
        statement.execute(SQL_Insert_Author_Query6);
        statement.execute(SQL_Insert_Author_Query7);

        statement.execute(SQL_Insert_AB_Query1);
        statement.execute(SQL_Insert_AB_Query2);
        statement.execute(SQL_Insert_AB_Query3);
        statement.execute(SQL_Insert_AB_Query4);
        statement.execute(SQL_Insert_AB_Query5);
        statement.execute(SQL_Insert_AB_Query6);
        statement.execute(SQL_Insert_AB_Query7);
    }
}
