package DTO.assembler;

import DTO.ListOfQuotesDTO;
import DTO.QuoteResponseDTO;
import DTO.QuoteWithRatingDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import controller.QuoteController;
import model.Quote;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class AssemblerListOfQuotesDTO {

    public static ListOfQuotesDTO createListOfQuotesDTO(List<QuoteWithRatingDTO> listOfQuotesWithRating) {
        ObjectMapper mapper = new ObjectMapper();
        ListOfQuotesDTO listOfQuotesDTOResponse = new ListOfQuotesDTO();
        listOfQuotesDTOResponse.setListOfDTO(listOfQuotesWithRating);
        listOfQuotesDTOResponse.addLink(linkTo(methodOn(QuoteController.class).all()).withSelfRel());
        return listOfQuotesDTOResponse;
    }
}
