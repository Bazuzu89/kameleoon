package DTO.assembler;

import DTO.QuoteResponseDTO;
import controller.QuoteController;
import model.Quote;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class AssemblerQuoteResponseDTO {
    public static QuoteResponseDTO createQuoteResponseDTO(Quote quote) {
        QuoteResponseDTO quoteResponseDTO = new QuoteResponseDTO();
        quoteResponseDTO.setContent(quote.getContent());
        quoteResponseDTO.setUserId(quote.getUserId());
        quoteResponseDTO.setDateOfCreation(quote.getDateOfCreation());
        quoteResponseDTO.setDateOfLastUpdate(quote.getDateOfLastUpdate());
        quoteResponseDTO.setVotes(quote.getVotes());
        quoteResponseDTO.setId(quote.getId());
        quoteResponseDTO.addLink(linkTo(methodOn(QuoteController.class).get(quote.getId())).withSelfRel());
        quoteResponseDTO.addLink(linkTo(methodOn(QuoteController.class).all()).withRel("quotes"));
        quoteResponseDTO.addLink(linkTo(methodOn(QuoteController.class).getVotes(quote.getId())).withRel("votes"));
        return quoteResponseDTO;
    }

}
