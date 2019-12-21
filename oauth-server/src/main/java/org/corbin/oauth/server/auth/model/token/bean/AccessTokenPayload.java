package org.corbin.oauth.server.auth.model.token.bean;

import lombok.Getter;
import lombok.Setter;
import org.corbin.oauth.server.auth.model.token.bean.AbstractTokenPayload;
import org.corbin.tools.common.util.IdHelper;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-10
 */
@Setter
@Getter
public class AccessTokenPayload extends AbstractTokenPayload {

  @Override
  public String createTicket() {
    return IdHelper.nextRandomUniqueId();
  }
}
