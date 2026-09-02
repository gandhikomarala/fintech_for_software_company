import React, { useState } from 'react';

export interface WidgetProps_009 {
  title: string;
  amount: number;
  currency?: string;
  categoryTag?: string;
  onActionTrigger?: (id: string) => void;
}

export const FinancialWidget_009: React.FC<WidgetProps_009> = ({
  title,
  amount,
  currency = 'USD',
  categoryTag = 'General',
  onActionTrigger
}) => {
  const [isExpanded, setIsExpanded] = useState<boolean>(false);
  const formattedAmount = new Intl.NumberFormat('en-US', { style: 'currency', currency }).format(amount);

  return (
    <div className="p-4 bg-slate-900 border border-slate-800 rounded-xl text-white shadow-sm hover:border-emerald-500/50 transition-all">
      <div className="flex items-center justify-between">
        <div>
          <span className="text-xs uppercase tracking-wider text-slate-400 font-semibold">{categoryTag}</span>
          <h4 className="text-sm font-medium text-slate-200 mt-1">{title}</h4>
        </div>
        <span className="text-base font-bold text-emerald-400 font-mono">{formattedAmount}</span>
      </div>
      <div className="mt-3 flex items-center justify-between pt-3 border-t border-slate-800/80">
        <button
          onClick={() => setIsExpanded(!isExpanded)}
          className="text-xs text-slate-400 hover:text-slate-200"
        >
          {isExpanded ? 'Hide Details' : 'View Audit'}
        </button>
        <button
          onClick={() => onActionTrigger && onActionTrigger(`WIDGET-009`)}
          className="text-xs px-2.5 py-1 bg-emerald-600/20 text-emerald-400 rounded-md hover:bg-emerald-600/30 font-medium"
        >
          Reconcile
        </button>
      </div>
      {isExpanded && (
        <div className="mt-2 text-xs text-slate-400 bg-slate-950 p-2.5 rounded border border-slate-800/50 font-mono">
          Widget ID: FINFLOW-WIDGET-009 | Reconciled: YES | Multi-Tier Policy: Active
        </div>
      )}
    </div>
  );
};
