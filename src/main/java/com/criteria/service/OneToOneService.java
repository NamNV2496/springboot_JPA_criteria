package com.criteria.service;

import com.criteria.domain.Field;
import com.criteria.domain.Folder;
import com.criteria.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OneToOneService {

    private final FolderRepository folderRepository;

    public Folder getItem() {
        return folderRepository.findById(1L).get();

    }
}
