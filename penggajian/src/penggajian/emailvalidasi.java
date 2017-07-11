/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penggajian;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFormattedTextField.AbstractFormatter;


/**
 *
 * @author Widi Ramadhan
 */
public class emailvalidasi extends AbstractFormatter {
    Pattern regexp = Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
 
    @Override
    public Object stringToValue(String string) throws ParseException {
        Matcher matcher = regexp.matcher(string);
        if(matcher.matches()){
            return string;
        }else{
            return "Masukan Email Yang Valid !!!";
        }
    }
 
    @Override
    public String valueToString(Object value) throws ParseException {
        return (String) value;
    }

}
