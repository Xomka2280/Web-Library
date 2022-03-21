package ru.rsreu.library.commands;

import javax.servlet.ServletException;
import java.io.IOException;

public class CreateBookCommand extends FrontCommand{
    @Override
    public void process() throws ServletException, IOException {
        forward("createBook");
    }
}
