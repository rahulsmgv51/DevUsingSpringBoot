package com.rahulsmgv.geoapp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentProvider implements ArgumentsProvider {

    @Override
    @Disabled
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return null;
    }
}
