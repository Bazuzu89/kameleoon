package DTO.assembler;

import DTO.ListOfGraphPointsDTO;
import DTO.VotingGraphDTO;
import controller.QuoteController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class AssemblerListOfGraphPointsDTO {

    public static ListOfGraphPointsDTO createListOfGraphPointsDTO(List<VotingGraphDTO> list, long quoteId) {
        ListOfGraphPointsDTO listOfGraphPointsDTO = new ListOfGraphPointsDTO();
        listOfGraphPointsDTO.setList(list);
        listOfGraphPointsDTO.setQuoteId(quoteId);
        listOfGraphPointsDTO.addLink(linkTo(methodOn(QuoteController.class).getGraph(quoteId)).withSelfRel());
        return listOfGraphPointsDTO;
    }
}
