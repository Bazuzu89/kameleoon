package DTO;

import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.List;

public class ListOfGraphPointsDTO {

    private List<VotingGraphDTO> list = new ArrayList<>();

    private List<Link> links = new ArrayList<>();
    private long quoteId;

    public List<VotingGraphDTO> getList() {
        return list;
    }

    public void setList(List<VotingGraphDTO> list) {
        this.list = list;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addLink(Link link) {
        links.add(link);
    }

    public void setQuoteId(long quoteId) {
        this.quoteId = quoteId;
    }
}
