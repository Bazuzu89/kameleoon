package DTO.assembler;

import DTO.VotesResponseDTO;

public class AssemblerVotesDTO {
    public static VotesResponseDTO createVotesDTO(int upVotes, int downVotes) {
        VotesResponseDTO votesResponseDTO = new VotesResponseDTO();
        votesResponseDTO.setUpVotes(upVotes);
        votesResponseDTO.setDownVotes(downVotes);
        return votesResponseDTO;
    }

}
