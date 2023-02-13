package DTO;

public class VotesDTO {
    int upVotes;
    int downVotes;

    public int getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }

    public static VotesDTO createVotesDTO(int upVotes, int downVotes) {
        VotesDTO votesDTO = new VotesDTO();
        votesDTO.setUpVotes(upVotes);
        votesDTO.setDownVotes(downVotes);
        return votesDTO;
    }
}
