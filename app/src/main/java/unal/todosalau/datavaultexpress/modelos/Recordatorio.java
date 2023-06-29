package unal.todosalau.datavaultexpress.modelos;

import java.time.LocalDate;

public class Recordatorio {
    private LocalDate localDate;
    private String radioButonSelection;
    private String title;
    private String selection;

    public Recordatorio(String radioButonSelection, String title, String selection){
        this.radioButonSelection = radioButonSelection;
        this.title = title;
        this.selection = selection;
        this.localDate = Date();
    }

    public LocalDate Date(){
        LocalDate fechaActual = LocalDate.now();
        return fechaActual;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public String getRadioButonSelection() {
        return radioButonSelection;
    }

    public void setRadioButonSelection(String radioButonSelection) {
        this.radioButonSelection = radioButonSelection;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }
}