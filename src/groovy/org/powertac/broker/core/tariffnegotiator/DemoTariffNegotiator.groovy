/*
 * Copyright (c) 2011 by the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.powertac.broker.core.tariffnegotiator

import org.apache.commons.logging.LogFactory
import org.powertac.broker.api.TariffNegotiator
import org.powertac.broker.infrastructure.messaging.MessageListenerRegistrar
import org.powertac.broker.interfaces.MessageListener
import org.powertac.common.TariffSpecification
import org.powertac.common.TariffTransaction

class DemoTariffNegotiator implements MessageListener, TariffNegotiator
{
  private static final log = LogFactory.getLog(this)

  MessageListenerRegistrar messageListenerRegistrar

  def initialize ()
  {
    messageListenerRegistrar.register(TariffSpecification, this)
    messageListenerRegistrar.register(TariffTransaction, this)
  }

  def onMessage (TariffSpecification ts)
  {
    log.debug("onMessage(TariffSpecification) - start")

    ts.save()

    log.debug("onMessage(TariffSpecification) - end")
  }

  def onMessage (TariffTransaction ttx)
  {
    log.debug("onMessage(TariffTransaction) - start")

    ttx.save()

    log.debug("onMessage(TariffTransaction) - receving ${ttx.txType} ttx for ${ttx.broker.username}")
    log.debug("onMessage(TariffTransaction) - ende")

  }

  def offerTariffs (tariffs)
  {
    // TODO: send tariffs to market
  }

  def setTariffOfferStatus (status)
  {
    // TODO: send final decision to market
  }
}