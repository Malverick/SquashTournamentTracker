package com.qa.projectbackend.services;

import com.qa.projectbackend.entity.Matches;
import com.qa.projectbackend.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchesService {

    private MatchRepository matchRepository;

    public MatchesService(@Autowired MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Matches> getAllMatches() {
        List<Matches> allMatchesList = (List<Matches>) matchRepository.findAll();
        return allMatchesList;
    }

    public Matches addMatch(Matches match) {
        matchRepository.save(match);
        return match;
    }

    public Optional<Matches> getMatch(Integer id) {
        return matchRepository.findById(id);
    }


    public Optional<Matches> updateMatch(Integer id, Matches match) {
        return matchRepository.findById(id).map(updatedMatch -> {
            if (match.getPlayer1() != null) { updatedMatch.setPlayer1(match.getPlayer1()); }
            if (match.getPlayer2() != null) { updatedMatch.setPlayer2(match.getPlayer2()); }
            updatedMatch.setP1score(match.getP1score());
            updatedMatch.setP2score(match.getP2score());
            if (match.getWinner() != null) { updatedMatch.setWinner(match.getWinner()); }
            if (match.getLooser() != null) { updatedMatch.setLooser(match.getLooser()); }
            return matchRepository.save(updatedMatch);
        });
    }

    public Integer deleteMatch(Integer id) {
        matchRepository.deleteById(id);
        return id;
    }
}
