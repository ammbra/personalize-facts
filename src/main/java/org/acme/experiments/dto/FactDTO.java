package org.acme.experiments.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbCreator;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class FactDTO {

    public static final String API = "api";
    public static final String USER = "user";
    public String _id;
    public String type;
    public String text;
    public String source;
    public StatusDTO statusDTO;
    public boolean deleted;

    public String user;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", shape = JsonFormat.Shape.STRING)
    public Date updatedAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", shape = JsonFormat.Shape.STRING)
    public Date createdAt;

    public boolean used;

    @JsonbCreator
    public static FactDTO empty() {
        FactDTO emptyDTO = new FactDTO();
        emptyDTO.source = USER;
        return emptyDTO;
    }


}
