路径约定：
/auth/password/login            GET account=xxx&password=yyy&orgId=xxx      => {setCookie(token, xxx) | response.Token=xxx}
/auth/sms/login                 GET mobile=xxx&code=yyy&orgId=xxx           => {setCookie(token, xxx) | response.Token=xxx} 
/auth/sms/registerAndLogin      GET mobile&code&orgId                       =>
/auth/sms/code                  GET mobile=                                 =>
/auth/sms/voice                 GET mobile=                                 =>
/auth/logout
/auth/password/register
/auth/...

用例：
excludes = {
    /auth/* ,
    
}


authChecking:
request: token <- cookie | header
response: pass | {code:500, msg:"access denied, Please login.", data=null}
