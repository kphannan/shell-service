package com.discover.loan.origination.throttle.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.discover.loan.origination.throttle.persistence.repository.ThrottleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith( MockitoExtension.class )
class ThrottleControllerTest
{
    @Mock
    private ThrottleRepository repository;

    @InjectMocks
    private ThrottleController controller;

    private MockMvc   mvc;


    // ----- Before -----
    @BeforeEach
    void setup()
    {
        mvc = MockMvcBuilders.standaloneSetup( controller )
                .setCustomArgumentResolvers( new PageableHandlerMethodArgumentResolver() )
                // .setControllerAdvice(new SuperHeroExceptionHandler())
                // .addFilters(new SuperHeroFilter())
                .build();
    }


    @Test
    void throttleController_eligibleGET_returnsPageable() throws Exception
    {

        final ArgumentCaptor<Pageable> pageableCaptor =
            ArgumentCaptor.forClass( Pageable.class );

        // mvc.perform( get("/index")
        //                     .contentType(MediaType.APPLICATION_JSON))
        //     .andExpect( status().isOk())
        //     .andExpect( content().contentTypeCompatibleWith("application/json"))
        //     .andExpect( jsonPath("$.greeting").value("Hello World"));

        final MockHttpServletResponse response =
        mvc.perform( get( "/loan/origination/throttle/eligible" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .accept( MediaType.APPLICATION_JSON )
                   )
            .andExpect( status().isOk() )
            // .andExpect( content().contentType( MediaType.APPLICATION_JSON_VALUE ) )
            // .andExpect( content().json( "{\"id\": 1234, \"name\": \"name\", \"ident\": \"abcd\"}" ) )
            .andDo( MockMvcResultHandlers.print() )
            .andReturn().getResponse();

        verify( repository ).findAll( pageableCaptor.capture() );
    }


}
