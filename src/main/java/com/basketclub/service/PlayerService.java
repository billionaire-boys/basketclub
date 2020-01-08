package com.basketclub.service;

import com.basketclub.domain.Player;
import com.basketclub.domain.SocialDetails;
import com.basketclub.domain.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Transactional(readOnly = true)
    public Player findByPlayerId(long id) throws NoSuchEntityException {
        return playerRepository.findById(id).orElseThrow(() -> new NoSuchPlayerException("해당 유저가 없습니다."));
    }

    public Player register(SocialDetails socialDetails) {
        return playerRepository.save(new Player(socialDetails));
    }

    public boolean isExistsBySocialId(long socialId) {
        return playerRepository.findBySocialDetailsSocialId(socialId).isPresent();
    }
}
