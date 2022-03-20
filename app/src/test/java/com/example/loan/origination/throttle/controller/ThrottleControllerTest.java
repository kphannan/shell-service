package com.example.loan.origination.throttle.controller;

import static com.example.support.PageableAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import com.example.loan.origination.throttle.persistence.repository.ThrottleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



/**
 * Unit test for ThrottleController.  Services/Repositories are to be mocked.
 * Proper calling of mocks is the goal, not the functionality inside the mocked calls.
 */
// Its ok to declare throws Exception on tests.  It makes for a clean compile, and any
// thrown exception is a test failure.
@SuppressWarnings( {  "PMD.SignatureDeclareThrowsException"
                    , "PMD.BeanMembersShouldSerialize" } )
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
                .andDo( MockMvcResultHandlers.print() )
                .andExpect( status().isOk() )
                // .andExpect( content().contentTypeCompatibleWith( "application/json" ) )
                // .andExpect( jsonPath("$.greeting" ).value("Hello World" ) )
                // .andExpect( content().contentType( MediaType.APPLICATION_JSON_VALUE ) )
                .andReturn().getResponse();

        // verify( repository ).findAll( any( PageRequest.class ) );
        verify( repository ).findAll( pageableCaptor.capture() );

        final PageRequest pageable = (PageRequest) pageableCaptor.getValue();


        assertAll(  () -> assertNotNull( response )
                  , () -> assertNotNull( response.getContentAsString() )
                  //   , () -> verify( repository ).findAll( pageableCaptor.capture() )
                  , () -> assertThat( pageable )
                            .hasPageNumber( 0 )
                            .hasPageSize( 20 )
        );
        // .hasSort( "name", Sort.Direction.ASC )
        // .hasSort( "id", Sort.Direction.DESC );


    }


    @Test
    void throttleController_eligiblePOST_returnsResponse() throws Exception
    {
        final String requestJson = "{"
                + "   \"appType\": 1,"
                + "   \"appId\":  \"0000000000000002\","
                + "   \"reengagementInd\": 1,"
                + "   \"offerMatch\": 2,"
                + "   \"offerCode\": 1,"
                + "   \"channelType\": \"chan01\""
                + "}";
        final MockHttpServletResponse response =
                mvc.perform( post( "/loan/origination/throttle/eligible" )
                                     .contentType( MediaType.APPLICATION_JSON )
                                     .content( requestJson )
                                     .characterEncoding( StandardCharsets.UTF_8 )
                                     .accept( MediaType.APPLICATION_JSON )
                           )
                   .andDo( MockMvcResultHandlers.print() )
                   .andExpect( status().isCreated() )
                   .andExpect( header().string( HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString() ) )
                   .andExpect( header().string( HttpHeaders.CONTENT_ENCODING, StandardCharsets.UTF_8.toString() ) )
                   // .andExpect( content().contentTypeCompatibleWith( "application/json" ) )
                   .andExpect( jsonPath( "$.applicationId" ).value( "0000000000000002" ) )
                   .andExpect( jsonPath( "$.throttleModelInd" ).value( 1 ) )
                   .andExpect( jsonPath( "$.throttleStrategyInd" ).value( 2 ) )
                   .andExpect( jsonPath( "$.throttlePriceInd" ).value( 1 ) )
                   // .andExpect( content().contentType( MediaType.APPLICATION_JSON_VALUE ) )
                   .andReturn().getResponse();

        // verify( repository ).findAll( any( PageRequest.class ) );

        assertAll(  () -> assertNotNull( response )
                  , () -> assertNotNull( response.getContentAsString() )
        );
    }

}
