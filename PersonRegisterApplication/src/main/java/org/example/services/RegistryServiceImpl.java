package org.example.services;

import org.example.dtos.PersonDetailsResponse;
import org.example.dtos.PersonIdRequest;
import org.example.dtos.StoreRecordDetails;
import org.example.exceptions.PersonAlreadyExistsException;
import org.example.exceptions.RecordNotFoundException;
import org.example.exceptions.WrongUserInputException;
import org.example.models.Person;
import org.example.models.Registry;
import org.springframework.stereotype.Service;

@Service
public class RegistryServiceImpl implements RegistryService{

    private final Registry registry;

    public RegistryServiceImpl() {
        this.registry = new Registry();
    }

    @Override
    public PersonDetailsResponse create(StoreRecordDetails userInput) {
        String validId = validatePersonDetails(userInput);
        Person alreadyRegistered = registry.getPersonById(validId);
        if (alreadyRegistered != null) {
            throw new PersonAlreadyExistsException();
        }
        Person personToAdd = new Person(validId,userInput.getFirstName(),userInput.getLastName());
        registry.getRegistry().add(personToAdd);
        return new PersonDetailsResponse(personToAdd);
    }

    @Override
    public PersonDetailsResponse delete(PersonIdRequest request) {
        Person person = validateIdAndGetPerson(request);
        registry.getRegistry().remove(person);
        return new PersonDetailsResponse(person);
    }

    @Override
    public PersonDetailsResponse search(PersonIdRequest request) {
        Person person = validateIdAndGetPerson(request);
        return new PersonDetailsResponse(person);
    }

    public Person validateIdAndGetPerson(PersonIdRequest request) {
        String validId = registry.validateId(request.getId());
        if (validId == null) {
            throw new WrongUserInputException("Id has to be in one of the following formats: YYMMDDXXXX or YYMMDD/XXXX");
        }
        Person person = registry.getPersonById(validId);
        if (person == null) {
            throw new RecordNotFoundException();
        } else {
            return person;
        }
    }
    public String validatePersonDetails(StoreRecordDetails details) {
        StringBuilder stringBuilder = new StringBuilder();
        if (details.getFirstName() == null || details.getFirstName().isEmpty()) {
            stringBuilder.append("Field firstName can not be empty. ");
        }
        if (details.getLastName() == null || details.getLastName().isEmpty()) {
            stringBuilder.append("Field lastName can not be empty. ");
        }

        String validId = registry.validateId(details.getId());
        if (validId == null) {
            stringBuilder.append("Id can not be omitted and has to be in one " +
                    "of the following formats: YYMMDDXXXX or YYMMDD/XXXX.");
        }
        if (!stringBuilder.isEmpty()) {
            throw new WrongUserInputException(stringBuilder.toString());
        }
        return validId;
    }
}
