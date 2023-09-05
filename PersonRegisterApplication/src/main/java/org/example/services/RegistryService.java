package org.example.services;

import org.example.dtos.PersonDetailsResponse;
import org.example.dtos.PersonIdRequest;
import org.example.dtos.StoreRecordDetails;

public interface RegistryService {

    PersonDetailsResponse create(StoreRecordDetails userInput);

    PersonDetailsResponse delete(PersonIdRequest request);

    PersonDetailsResponse search(PersonIdRequest request);
}
