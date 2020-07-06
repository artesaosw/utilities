package dev.craftsmanship.utils.values;

import dev.craftsmanship.utils.values.Text;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TextConverter implements AttributeConverter<Text,String> {

    @Override
    public String convertToDatabaseColumn(Text text) {
        return (String) text.getInternalValue();
    }

    @Override
    public Text convertToEntityAttribute(String s) {
        Text text = new Text();
        text.setInternalValue(s);
        return text;
    }
}
