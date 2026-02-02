package com.joshuafeld.athly.workout.service;

import com.joshuafeld.athly.workout.dto.SetDto;
import com.joshuafeld.athly.workout.dto.SetPatchDto;
import com.joshuafeld.athly.workout.dto.SetPostDto;
import com.joshuafeld.athly.workout.dto.SetPutDto;
import com.joshuafeld.athly.workout.mapper.SetMapper;
import com.joshuafeld.athly.workout.model.Set;
import com.joshuafeld.athly.workout.repository.SegmentRepository;
import com.joshuafeld.athly.workout.repository.SetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * A set service.
 */
@Service
public class SetService {

    private final SetRepository repository;
    private final SegmentRepository segmentRepository;

    private final SetMapper mapper;

    /**
     * Creates an instance of an {@code SetService} class.
     *
     * @param repository the value for the {@code repository} component
     * @param segmentRepository the value for the {@code segmentRepository}
     *                          component
     * @param mapper the value for the {@code mapper} component
     */
    public SetService(final SetRepository repository,
                      final SegmentRepository segmentRepository,
                      final SetMapper mapper) {
        this.repository = repository;
        this.segmentRepository = segmentRepository;
        this.mapper = mapper;
    }

    /**
     * Creates a new set.
     *
     * @param segment the id of the segment
     * @param dto the data for the segment
     * @return the data of the segment
     */
    @Transactional
    public SetDto post(final Long segment, final SetPostDto dto) {
        return mapper.toDto(repository.save(new Set(
                segmentRepository.requireById(segment),
                dto.type(), dto.weight(), dto.reps(), dto.done()
        )));
    }

    /**
     * Returns the data of all sets.
     *
     * @param segment the id of the segment
     * @return a list of all sets' data
     */
    @Transactional(readOnly = true)
    public List<SetDto> get(final Long segment) {
        return repository.findAll(segment).stream().map(mapper::toDto).toList();
    }

    /**
     * Returns the data of the set with the given id.
     *
     * @param segment the id of the segment
     * @param id the id of the set
     * @return the data of the set
     */
    @Transactional(readOnly = true)
    public SetDto get(final Long segment, final Long id) {
        return mapper.toDto(repository.requireById(id));
    }

    /**
     * Partially updates the data of the set with the given id.
     *
     * @param id the id of the set
     * @param dto the data for the set
     * @return the data of the set
     */
    @Transactional
    public SetDto patch(final Long id, final SetPatchDto dto) {
        Set set = repository.requireById(id);
        Optional.ofNullable(dto.type()).ifPresent(set::type);
        Optional.ofNullable(dto.weight()).ifPresent(set::weight);
        Optional.ofNullable(dto.reps()).ifPresent(set::reps);
        Optional.ofNullable(dto.done()).ifPresent(set::done);
        return mapper.toDto(repository.save(set));
    }

    /**
     * Updates the data of the set with the given id.
     *
     * @param id the id of the set
     * @param dto the data for the set
     * @return the data of the set
     */
    @Transactional
    public SetDto put(final Long id, final SetPutDto dto) {
        Set set = repository.requireById(id);
        set.type(dto.type());
        set.weight(dto.weight());
        set.reps(dto.reps());
        set.done(dto.done());
        return mapper.toDto(repository.save(set));
    }

    /**
     * Deletes the data of the set with the given id.
     *
     * @param id the id of the set
     */
    @Transactional
    public void delete(final Long id) {
        repository.deleteById(id);
    }
}
