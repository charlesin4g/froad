package mediinfo.java.tt.froad.models.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class GeometryConverter implements AttributeConverter<String, String> {
    @Override
    public String convertToDatabaseColumn(String attribute) {
        return attribute;
    }
    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }
} 