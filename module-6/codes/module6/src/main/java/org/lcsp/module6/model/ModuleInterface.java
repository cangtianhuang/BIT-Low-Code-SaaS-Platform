package org.lcsp.module6.model;

import java.util.List;

public class ModuleInterface {
    private Long id;
    private String name; // Name of the interface
    private String description; // Description of the interface
    private List<String> input; // Input parameters of the interface
    private List<String> output; // Output of the interface

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getInput() {
        return input;
    }

    public void setInput(List<String> input) {
        this.input = input;
    }

    public List<String> getOutput() {
        return output;
    }

    public void setOutput(List<String> output) {
        this.output = output;
    }
}