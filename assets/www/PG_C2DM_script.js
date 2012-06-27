//alert( localStorage.site );
gApp = new Array();

gApp.deviceready = false;
gApp.c2dmregid = '';

window.onbeforeunload  =  function(e) {

    if ( gApp.c2dmregid.length > 0 )
    {
      // The same routines are called for success/fail on the unregister. You can make them unique if you like
      window.plugins.C2DM.unregister( C2DM_Success, C2DM_Fail );      // close the C2DM

    }
};





  alert('Mobileinit event received' );

  document.addEventListener('deviceready', function() {
    // This is the PhoneGap deviceready event. Once this is called PhoneGap is available to be used
    alert('deviceready event received' );

    alert('calling C2DM.register, register our email with Google' );


    gApp.DeviceReady = true;

    // Some Unique stuff here,
    // The first Parm is your Google email address that you were authorized to use C2DM with
    // the Event Processing rountine (2nd parm) we pass in the String name
    // not a pointer to the routine, under the covers a JavaScript call is made so the name is used
    // to generate the function name to call. I didn't know how to call a JavaScript routine from Java
    // The last two parms are used by PhoneGap, they are the callback routines if the call is successful or fails
    //
    // CHANGE: your_c2dm_account@gmail.com
    // TO: what ever your C2DM authorized email account name is
    //
    window.plugins.C2DM.register("your_c2dm_account@gmail.com", "C2DM_Event", C2DM_Success, C2DM_Fail );

  }, false );


function
C2DM_Event(e)
{

  alert('EVENT -> RECEIVED:' + e.event);


  switch( e.event )
  {
  case 'registered':
    // the definition of the e variable is json return defined in C2DMReceiver.java
    // In my case on registered I have EVENT and REGID defined
    gApp.c2dmregid = e.regid;
    if ( gApp.c2dmregid.length > 0 )
    {
      alert('REGISTERED -> REGID:' + e.regid);


      // ==============================================================================
      // ==============================================================================
      // ==============================================================================
      //
      // This is where you would code to send the REGID to your server for this device
      //
      // ==============================================================================
      // ==============================================================================
      // ==============================================================================

    }

    break

  case 'message':
    // the definition of the e variable is json return defined in C2DMReceiver.java
    // In my case on registered I have EVENT, MSG and MSGCNT defined

    // You will NOT receive any messages unless you build a HOST server application to send
    // Messages to you, This is just here to show you how it might work

    alert('MESSAGE -> MSG:' + e.msg);

    alert('MESSAGE -> MSGCNT:' + e.msgcnt);


    break;


  case 'error':

    alert('ERROR -> MSG:' + e.msg);

    break;



  default:
    alert('EVENT -> Unknown, an event was received and we do not know what it is');

    break;
  }
}

function
C2DM_Success(e)
{
  alert('C2DM_Success -> We have successfully registered and called the C2DM plugin, waiting for C2DM_Event:reistered -> REGID back from Google');

}

function
C2DM_Fail(e)
{
  alert('C2DM_Fail -> C2DM plugin failed to register');

  alert('C2DM_Fail -> ' + e.msg);

}

