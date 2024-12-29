package org.example;

public class Task3 {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        editor.setFormatter(new UpperCaseFormatter());
        editor.formatText("haris");
        editor.setFormatter(new LowerCaseFormatter());
        editor.formatText("HARIS");
        editor.setFormatter(new CamelCaseFormatter());
        editor.formatText("haris");
    }
}
interface TextFormatter{
    String formatText(String text);
}
class UpperCaseFormatter implements TextFormatter{
    @Override
    public String formatText(String text) {
        return text.toUpperCase();
    }
}
class LowerCaseFormatter implements TextFormatter{
    @Override
    public String formatText(String text){
        return text.toLowerCase();
    }
}
class CamelCaseFormatter implements TextFormatter{
    @Override
    public String formatText(String text){
        String result = new String();
        for(int i=0;i<text.length();i++){
            if(i%2==0){
                result+= Character.toLowerCase(text.charAt(i));
            }
            else{
                result+= Character.toUpperCase(text.charAt(i));
            }
        }
        return result;
    }
}

class TextEditor{
    private TextFormatter formatter;
    public void setFormatter(TextFormatter formatter){
        this.formatter = formatter;
    }
    public void formatText(String text){
        if (formatter != null) {
            System.out.println(formatter.formatText(text));
        } else {
            System.out.println("No formatter set.");
        }
    }
}
